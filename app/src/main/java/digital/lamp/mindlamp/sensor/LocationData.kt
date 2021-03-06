package digital.lamp.mindlamp.sensor

import android.content.Context
import digital.lamp.lamp_kotlin.sensor_core.Lamp
import digital.lamp.lamp_kotlin.sensor_core.Locations
import digital.lamp.mindlamp.utils.LampLog
import java.lang.Exception
import digital.lamp.lamp_kotlin.lamp_core.models.DimensionData
import digital.lamp.lamp_kotlin.lamp_core.models.SensorEvent
import digital.lamp.mindlamp.utils.Sensors

/**
 * Created by ZCO Engineering Dept. on 06,February,2020
 */
class LocationData constructor(sensorListener: SensorListener, context: Context){
   init {
       try {
           //Location Settings
           Lamp.startLocations(context)

           //Location Observer
           Locations.setSensorObserver{ data ->
                LampLog.e(data.toString())
               val dimensionData =
                   DimensionData(
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       data.longitude,
                       data.latitude,
                       data.altitude,
                       data.accuracy,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,null,null,null,null
                   )
               val sensorEventData =
                   SensorEvent(
                       dimensionData,
                       Sensors.GPS.sensor_name,System.currentTimeMillis().toDouble()
                   )
               LampLog.e("Location : ${data.latitude} : ${data.longitude}")
               sensorListener.getLocationData(sensorEventData)

           }
       }catch (ex : Exception){
           ex.printStackTrace()
       }
   }
}

