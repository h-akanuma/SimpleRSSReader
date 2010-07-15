package jp.co.nikkeibp.itpro.rssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;

public class RssParserTask extends AsyncTask<String, Integer, RssListAdapter> {
	private RssReaderActivity mActivity;
	private RssListAdapter mAdapter;
	private ProgressDialog mProgressDialog;
	
	public RssParserTask(RssReaderActivity activity, RssListAdapter adapter) {
		mActivity = activity;
		mAdapter = adapter;
	}

	@Override
	protected RssListAdapter doInBackground(String... params) {
		RssListAdapter result = null;
		try {
			URL url = new URL(params[0]);
			InputStream is = url.openConnection().getInputStream();
			result = parseXml(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPreExecute() {
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage("Now Loading");
		mProgressDialog.show();
	}

	@Override
	protected void onPostExecute(RssListAdapter result) {
		mProgressDialog.dismiss();
		mActivity.setListAdapter(result);
	}
	
	// メインスレッド上で実行される
	public RssListAdapter parseXml(InputStream is) throws IOException,XmlPullParserException {
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, null);
			int eventType = parser.getEventType();
			Item currentItem = null;
			while(eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch(eventType) {
					case XmlPullParser.START_TAG:
						tag = parser.getName();
						if (tag.equals("item")) {
							currentItem = new Item();
						} else if (currentItem != null) {
							if (tag.equals("title")) {
								currentItem.setmTitle(parser.nextText());
							} else if (tag.equals("description")) {
								currentItem.setmDescription(parser.nextText());
							}
						}
						break;
					case XmlPullParser.END_TAG:
						tag = parser.getName();
						if (tag.equals("item")) {
							mAdapter.add(currentItem);
						}
						break;
				}
				eventType = parser.next();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mAdapter;
	}
}
