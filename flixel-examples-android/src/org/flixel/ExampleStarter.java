package org.flixel;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExampleStarter extends ListActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.demos)));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		String exampleName = (String) (getListAdapter().getItem(position));
		try
		{
			Class<?> c = Class.forName("org.flixel." + exampleName);
			Intent intent = new Intent(this, c);
			startActivity(intent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}