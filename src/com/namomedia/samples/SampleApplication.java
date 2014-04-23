package com.namomedia.samples;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class SampleApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    ParseObject.registerSubclass(BoardItem.class);
    Parse.initialize(this, "U8ipUhuguVcq8zid2nIH1OU7jvwYuBi2G2QBzUqT",
        "JYq1sRB1ZBh6AmD0UvGNVsLq5DwyIr5PnZikF1QN");

    // Replace "11aa69fe03877062" with your device's ID. When you initialize the SDK
    // your device's ID will be printed to the console in an I/Namo level logging message.
    // Namo.setTestDevices(true, "11aa69fe03877062");
  }
}
