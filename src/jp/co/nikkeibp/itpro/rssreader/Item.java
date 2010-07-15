package jp.co.nikkeibp.itpro.rssreader;

public class Item {

	private CharSequence mTitle;
	
	private CharSequence mDescription;
	
	public Item() {
		mTitle = "";
		mDescription = "";
	}

	public CharSequence getmTitle() {
		return mTitle;
	}

	public void setmTitle(CharSequence mTitle) {
		this.mTitle = mTitle;
	}

	public CharSequence getmDescription() {
		return mDescription;
	}

	public void setmDescription(CharSequence mDescription) {
		this.mDescription = mDescription;
	}
	
}
