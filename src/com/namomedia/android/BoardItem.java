package com.namomedia.android;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("BoardItem")
public class BoardItem extends ParseObject {

  public String getTitle() {
    return getString("title");
  }

  public int getLikes() {
    return getInt("countLikes");
  }

  public int getRepins() {
    return getInt("countRepins");
  }

  public String getRepinnedBy() {
    return getString("repinnedBy");
  }

  public ParseFile getPicture() {
    return getParseFile("picture");
  }

  public ParseFile getRepinnedByPicture() {
    return getParseFile("repinnedByPicture");
  }
}
