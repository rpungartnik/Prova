package br.com.pung.prova;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

// teste
public class MainActivity extends Activity implements SensorEventListener {

	SensorManager sMgr;
	Sensor sensorPresure;
	Sensor sensorLight;
	Sensor sensorAmbTemp;
	Sensor sensorTemp;
	Sensor sensorHumid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sMgr = (SensorManager)getSystemService( SENSOR_SERVICE );
        sensorPresure = sMgr.getDefaultSensor( Sensor.TYPE_PRESSURE );
        sensorLight = sMgr.getDefaultSensor( Sensor.TYPE_LIGHT );
        sensorAmbTemp = sMgr.getDefaultSensor( Sensor.TYPE_AMBIENT_TEMPERATURE );
        sensorTemp = sMgr.getDefaultSensor( Sensor.TYPE_RELATIVE_HUMIDITY );
        sensorHumid = sMgr.getDefaultSensor( Sensor.TYPE_TEMPERATURE );
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
      // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
    	if( event.sensor.getType() == Sensor.TYPE_LIGHT )
    	{
    		StringBuilder data = new StringBuilder();
    		data.append( event.values[0] + "lux" );
    		
    		TextView sensorsData = (TextView)findViewById( R.id.TextLux );
    		
    		sensorsData.setText( data );
    	}
    	if( event.sensor.getType() == Sensor.TYPE_PRESSURE )
    	{
    		StringBuilder data = new StringBuilder();
    		data.append( event.values[0] + "mBar" );
    		
    		TextView sensorsData = (TextView)findViewById( R.id.TextPressure );
    		
    		sensorsData.setText( data );
    	}
    	if( event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE )
    	{
    		StringBuilder data = new StringBuilder();
    		data.append( event.values[0] + " Amb" );
    		
    		TextView sensorsData = (TextView)findViewById( R.id.textView4);
    		
    		sensorsData.setText( data );
    	}
    	if( event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY )
    	{
    		StringBuilder data = new StringBuilder();
    		data.append( event.values[0] + " Rel" );
    		
    		TextView sensorsData = (TextView)findViewById( R.id.textView5 );
    		
    		sensorsData.setText( data );
    	}
    	if( event.sensor.getType() == Sensor.TYPE_TEMPERATURE )
    	{
    		StringBuilder data = new StringBuilder();
    		data.append( event.values[0] + " Temp" );
    		
    		TextView sensorsData = (TextView)findViewById( R.id.textView6 );
    		
    		sensorsData.setText( data );
    	}
      // Do something with this sensor data.
    }

    @Override
    protected void onResume() {
      // Register a listener for the sensor.
      super.onResume();
      if( sensorPresure != null )
    	  sMgr.registerListener( this, sensorPresure, SensorManager.SENSOR_DELAY_NORMAL );
      if( sensorLight != null )
    	  sMgr.registerListener( this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL );
      if( sensorAmbTemp != null )
    	  sMgr.registerListener( this, sensorAmbTemp, SensorManager.SENSOR_DELAY_NORMAL );
      if( sensorTemp != null )
    	  sMgr.registerListener( this, sensorTemp, SensorManager.SENSOR_DELAY_NORMAL );
      if( sensorHumid != null )
    	  sMgr.registerListener( this, sensorHumid, SensorManager.SENSOR_DELAY_NORMAL );
    }

    @Override
    protected void onPause() {
      // Be sure to unregister the sensor when the activity pauses.
      super.onPause();
      sMgr.unregisterListener( this );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch ( id )
        {
        	case R.id.action_config:
                Intent ConfigIntent = new Intent( this, ConfigActivity.class );
                startActivity( ConfigIntent );      		
        		break;

        	case R.id.action_help:
                Intent HelpIntent = new Intent( this, HelpActivity.class );
                startActivity( HelpIntent );  
        		break;

        	case R.id.action_about:
                Intent AboutIntent = new Intent( this, AboutActivity.class );
                startActivity( AboutIntent );
        		break;

        	case R.id.action_exit:
        		System.exit( 0 );
        		break;

        	default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
