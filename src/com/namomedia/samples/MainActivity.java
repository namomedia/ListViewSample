package com.namomedia.samples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.namomedia.android.Namo;
import com.namomedia.android.NamoListAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends Activity {

  private ItemAdapter itemAdapter;
  private NamoListAdapter namoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("ListViewSample");
    setContentView(R.layout.activity_main);

    // Set up list adapter.
    itemAdapter = new ItemAdapter(this);
    namoAdapter = Namo.createListAdapter(this, itemAdapter);
    namoAdapter.registerAdLayout(R.layout.namo_ad_item, "test-tracking-id");
    ListView listView = (ListView) findViewById(R.id.listview);
    listView.setAdapter(namoAdapter);

    // Query the Parse API for data.
    ParseQuery<BoardItem> query = ParseQuery.getQuery(BoardItem.class);
    query.findInBackground(new FindCallback<BoardItem>() {
      @Override
      public void done(List<BoardItem> objects, ParseException e) {
        for (BoardItem item : objects) {
          itemAdapter.add(item);
        }
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    // namoAdapter.requestAds();
  }

  static class ItemAdapter extends ArrayAdapter<BoardItem> {

    private final Context context;

    public ItemAdapter(Context context) {
      super(context, 0);
      this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;
      if (convertView == null) {
        view = LayoutInflater.from(context).inflate(R.layout.board_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
      }
      ViewHolder holder = (ViewHolder) view.getTag();

      BoardItem item = getItem(position);
      holder.title.setText(item.getTitle());
      holder.repinnedBy.setText(item.getRepinnedBy());
      holder.repins.setText(item.getRepins() + " repins");
      holder.likes.setText(item.getLikes() + " likes");

      holder.picture.setParseFile(item.getPicture());
      holder.picture.loadInBackground();
      holder.repinnedPicture.setParseFile(item.getRepinnedByPicture());
      holder.repinnedPicture.loadInBackground();

      return view;
    }
  }

  private static class ViewHolder {

    final ParseImageView picture;
    final TextView title;
    final ParseImageView repinnedPicture;
    final TextView repinnedBy;
    final TextView repins;
    final TextView likes;

    ViewHolder(View view) {
      picture = (ParseImageView) view.findViewById(R.id.picture);
      title = (TextView) view.findViewById(R.id.title);
      repinnedPicture = (ParseImageView) view.findViewById(R.id.repinned_picture);
      repinnedBy = (TextView) view.findViewById(R.id.repinned_by);
      repins = (TextView) view.findViewById(R.id.repins);
      likes = (TextView) view.findViewById(R.id.likes);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
