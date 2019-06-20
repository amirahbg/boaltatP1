package com.example.boaltatp1.data.image.source;

import com.example.boaltatp1.data.image.Image;

import java.util.List;

import io.reactivex.Flowable;

public interface ImageDS {
    Flowable<Long> insertImage(Image image);

    Flowable<List<Image>> getAdvImages(Long advId);
}
