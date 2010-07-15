package jp.co.nikkeibp.itpro.rssreader;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RssListAdapter extends ArrayAdapter<Item>{

	private LayoutInflater mInflater;
	private TextView mTitle;
	private TextView mDescr;
	
	public RssListAdapter(Context context, List<Item> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if(convertView == null) {
			view = mInflater.inflate(R.layout.item_row, null);
		}
		
		Item item = this.getItem(position);
		if (item != null) {
			String title = item.getmTitle().toString();
			mTitle = (TextView)view.findViewById(R.id.item_title);
			mTitle.setText(title);
			String descr = item.getmDescription().toString();
			mDescr = (TextView)view.findViewById(R.id.item_descr);
			mDescr.setText(descr);
		}
		
		return view;
	}
	
	
}
