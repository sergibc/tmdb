package com.sergibc.tmdb.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Model of a movie item for presentation layer
 */
@Data
public class MovieItemViewModel implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieItemViewModel> CREATOR = new Parcelable.Creator<MovieItemViewModel>() {
        @Override
        public MovieItemViewModel createFromParcel(Parcel in) {
            return new MovieItemViewModel(in);
        }

        @Override
        public MovieItemViewModel[] newArray(int size) {
            return new MovieItemViewModel[size];
        }
    };

    private String imagePath;

    private String overview;

    private String year;

    private int id;

    private String title;

    public MovieItemViewModel() {

    }

    protected MovieItemViewModel(Parcel in) {
        imagePath = in.readString();
        overview = in.readString();
        year = in.readString();
        id = in.readInt();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeString(overview);
        dest.writeString(year);
        dest.writeInt(id);
        dest.writeString(title);
    }

}
