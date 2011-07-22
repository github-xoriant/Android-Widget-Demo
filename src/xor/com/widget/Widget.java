/*
 * Copyright (C) 2009 nEx.Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xor.com.widget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider
{    
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) 
    {
    	Timer timer = new Timer();
    	timer.scheduleAtFixedRate(new Time(context, appWidgetManager), 1, 1000);
    	
    	
    }


	private class Time extends TimerTask 
	{
		RemoteViews views;
		AppWidgetManager appWidgetManager;
		ComponentName currentWidget;
		Context context;
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT,
				Locale.getDefault());
	
		public Time(Context context, AppWidgetManager appWidgetManager) 
		{
			this.context = context;
			this.appWidgetManager = appWidgetManager;
			views = new RemoteViews(context.getPackageName(), R.layout.widget);
			currentWidget = new ComponentName(context, Widget.class);
		}
	
		@Override
		public void run() 
		{
			Intent informationIntent = new Intent(context,info.class);
	    	PendingIntent infoPendingIntent = PendingIntent.getActivity(context, 0, informationIntent, 0);
	    	views.setOnClickPendingIntent(R.id.Widget, infoPendingIntent);
			views.setTextViewText(R.id.widget_textview,format.format(new Date()));
			appWidgetManager.updateAppWidget(currentWidget, views);
		}
	}

}
