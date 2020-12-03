package digital.lamp.mindlamp.sensor

import android.content.Context
import com.mindlamp.Accelerometer
import com.mindlamp.Lamp
import com.mindlamp.Lamp_Preferences
import com.mindlamp.providers.Accelerometer_Provider
import digital.lamp.mindlamp.R
import digital.lamp.mindlamp.network.model.DimensionData
import digital.lamp.mindlamp.network.model.LogEventRequest
import digital.lamp.mindlamp.network.model.SensorEventData
import digital.lamp.mindlamp.utils.LampLog
import digital.lamp.mindlamp.utils.Utils

/**
 * Created by ZCO Engineering Dept. on 05,February,2020
 */
class AccelerometerData constructor(sensorListener: SensorListener, context:Context) {
     init {
         try {
             //Accelerometer settings
             Lamp.setSetting(
                 context,
                 Lamp_Preferences.FREQUENCY_ACCELEROMETER,
                 200000
             ) //20Hz
             Lamp.setSetting(context, Lamp_Preferences.THRESHOLD_ACCELEROMETER, 5f)
             Lamp.startAccelerometer(context)//start sensor
             //Sensor Observer
             Accelerometer.setSensorObserver {
                 val x = it.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_0)
                 val y = it.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_1)
                 val z = it.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_2)
                 if (it != null) {
                     val dimensionData =
                         DimensionData(
                             x,
                             y,
                             z,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,
                             null,null,null,null
                         )
                     val sensorEventData =
                         SensorEventData(
                             dimensionData,
                             "lamp.accelerometer",System.currentTimeMillis().toDouble()
                         )

                     LampLog.e("Accelerometer : $x : $y : $z")
//                     Aware.stopAccelerometer(context)
                     sensorListener.getAccelerometerData(sensorEventData)
                 }else{
                     val logEventRequest = LogEventRequest()
                     logEventRequest.message = context.getString(R.string.log_accelerometer_null)
                     LogUtils.invokeLogData(Utils.getApplicationName(context), context.getString(R.string.warning), logEventRequest)
                 }
             }
         }catch (ex:Exception){
             ex.printStackTrace()
             val logEventRequest = LogEventRequest()
             logEventRequest.message = context.getString(R.string.log_accelerometer_error)
             LogUtils.invokeLogData(Utils.getApplicationName(context), context.getString(R.string.error), logEventRequest)
         }
    }
}