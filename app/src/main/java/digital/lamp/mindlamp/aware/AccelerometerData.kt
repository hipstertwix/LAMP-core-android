package digital.lamp.mindlamp.aware

import android.content.Context
import com.aware.Accelerometer
import com.aware.Aware
import com.aware.Aware_Preferences
import com.aware.providers.Accelerometer_Provider
import digital.lamp.mindlamp.appstate.AppState
import digital.lamp.mindlamp.network.model.DimensionData
import digital.lamp.mindlamp.network.model.LogEventRequest
import digital.lamp.mindlamp.network.model.SensorEventRequest
import digital.lamp.mindlamp.network.model.UserAgent
import digital.lamp.mindlamp.repository.LampForegroundService
import digital.lamp.mindlamp.utils.Utils


/**
 * Created by ZCO Engineering Dept. on 05,February,2020
 */
class AccelerometerData constructor(awareListener: AwareListener, context:Context) {
     init {
         try {
             //Accelerometer settings
             Aware.setSetting(
                 context,
                 Aware_Preferences.FREQUENCY_ACCELEROMETER,
                 200000
             ) //20Hz
             Aware.setSetting(context, Aware_Preferences.THRESHOLD_ACCELEROMETER, 0.02f)
             Aware.startAccelerometer(context)//start sensor
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
                             null
                         )
                     val sensorEventRequest =
                         SensorEventRequest(
                             dimensionData,
                             "lamp.accelerometer",
                             System.currentTimeMillis()
                         )
                     Aware.stopAccelerometer(context)
                     awareListener.getAccelerometerData(sensorEventRequest)
                 }else{
                     val logEventRequest = LogEventRequest("Null Caught Accelerometer Data", UserAgent(), AppState.session.userId)
                     LogUtils.invokeLogData(Utils.getApplicationName(context), "warning", logEventRequest)
                 }
             }
         }catch (ex:Exception){
             ex.printStackTrace()
             val logEventRequest = LogEventRequest("Exception Caught Accelerometer", UserAgent(), AppState.session.userId)
             LogUtils.invokeLogData(Utils.getApplicationName(context), "error", logEventRequest)
         }
    }
}