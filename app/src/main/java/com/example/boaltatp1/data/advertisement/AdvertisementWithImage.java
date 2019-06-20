package com.example.boaltatp1.data.advertisement;


import com.example.boaltatp1.data.image.Image;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AdvertisementWithImage {
    @Embedded
    public Advertisement mAdvertisement;

    @Relation(parentColumn = "id", entityColumn = "advertisementId")
    public List<Image> mImages;
}
