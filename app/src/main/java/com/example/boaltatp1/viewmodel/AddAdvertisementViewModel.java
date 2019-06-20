package com.example.boaltatp1.viewmodel;

import android.app.Application;
import android.net.Uri;

import com.example.boaltatp1.data.advertisement.Advertisement;
import com.example.boaltatp1.data.advertisement.source.AdvertisementRepo;
import com.example.boaltatp1.data.image.Image;
import com.example.boaltatp1.data.image.source.ImageRepo;
import com.example.boaltatp1.data.user.source.UserRepo;
import com.example.boaltatp1.util.Injection;
import com.example.boaltatp1.util.ObservableViewModel;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddAdvertisementViewModel extends ObservableViewModel {
    private final ImageRepo mImageRepo;
    private final AdvertisementRepo mAdvertisementRepo;
    private final UserRepo mUserRepo;
    private MutableLiveData<Boolean> mIsSuccessFullyAdded;
    private long mUserId;

    public AddAdvertisementViewModel(@NonNull Application application) {
        super(application);
        mAdvertisementRepo = Injection.provideAdvertisementRepo(getApplication());
        mUserRepo = Injection.provideUserRepo(getApplication());
        mImageRepo = Injection.provideImageRepo(getApplication());

        mIsSuccessFullyAdded = new MutableLiveData<>();
        mUserId = mUserRepo.getUserId();
    }

    public void requestAddAdvertisement(String title, String body, String price,
                                        Uri uri, String videoPath) {
        Advertisement advertisement = new Advertisement();
        advertisement.setUserId(mUserId);
        advertisement.setTitle(title);
        advertisement.setBody(body);
        advertisement.setPrice(Integer.parseInt(price));
        advertisement.setVideoUrl(videoPath);
        advertisement.setConfDate(new Date(0));
        getCompositeDisposable().add(mAdvertisementRepo.insertAdvertisement(advertisement)
                .flatMap(l -> mImageRepo.insertImage(
                        new Image(l, uri.getPath(),
                                Calendar.getInstance().getTime())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r -> {
                    if (r > 0)
                        mIsSuccessFullyAdded.setValue(true);
                    else
                        mIsSuccessFullyAdded.setValue(false);
                }, e -> {
                    mIsSuccessFullyAdded.setValue(false);
                }));
    }

    public MutableLiveData<Boolean> getIsSuccessFullyAdded() {
        return mIsSuccessFullyAdded;
    }
}
