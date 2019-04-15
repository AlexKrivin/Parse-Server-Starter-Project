/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

   ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

   query.whereEqualTo("username","Alex");
   query.setLimit(1);
   query.findInBackground(new FindCallback<ParseObject>() {
       @Override
       public void done(List<ParseObject> objects, ParseException e) {
           if (e==null){
               Log.i("findInBackground","Retrieved "+ objects.size()+" objects");
               if(objects.size()>0){
                   for(ParseObject object:objects){
                       Log.i("findInBackground", Integer.toString(object.getInt("score")));
                       Toast.makeText(getApplicationContext(), "score is "+Integer.toString(object.getInt("score")), Toast.LENGTH_SHORT).show();
                   }
               }
           }
       }
   });






    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}

/*

ParseObject score  = new ParseObject("Score");
   score.put("username","Rob");
   score.put("score",86);
   score.saveInBackground(new SaveCallback() {
     @Override
     public void done(ParseException e) {
       if (e==null){
         Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
       }
       else {
         Toast.makeText(getApplicationContext(), ("fail save"+e.toString()), Toast.LENGTH_SHORT).show();
       }
     }
   });

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.getInBackground("SA6Z1P0NXj", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
            if(e==null&&object!=null){
                object.put("score",200);
                object.saveInBackground();
                Toast.makeText(getApplicationContext(), object.getString("username"), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), Integer.toString(object.getInt("score")), Toast.LENGTH_LONG).show();
            }
            else{

            }
          }
      });
      -----------------------------------------------------------------------------------
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

   query.whereEqualTo("username","Alex");
   query.setLimit(1);
   query.findInBackground(new FindCallback<ParseObject>() {
       @Override
       public void done(List<ParseObject> objects, ParseException e) {
           if (e==null){
               Log.i("findInBackground","Retrived "+ objects.size()+" objects");
               if(objects.size()>0){
                   for(ParseObject object:objects){
                       Log.i("findInBackground", object.getString("username"));
                   }
               }
           }
       }
   });
 */