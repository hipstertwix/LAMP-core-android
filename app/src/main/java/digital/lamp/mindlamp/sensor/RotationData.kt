package digital.lamp.mindlamp.sensor

import android.content.Context
import com.mindlamp.Lamp
import com.mindlamp.Lamp_Preferences
import com.mindlamp.Rotation
import com.mindlamp.providers.Rotation_Provider
import digital.lamp.mindlamp.R
import digital.lamp.mindlamp.network.model.*
import digital.lamp.mindlamp.network.model.RotationData
import digital.lamp.mindlamp.utils.LampLog
import digital.lamp.mindlamp.utils.Utils

/**
 * Created by ZCO Engineering Dept. on 06,February,2020
 */
class RotationData constructor(sensorListener: SensorListener, context: Context){
    init {
        try {
            //Rotation Sensor Settings
            Lamp.setSetting(
                context,
                Lamp_Preferences.FREQUENCY_ROTATION,
                200000
            ) //20Hz
            Lamp.setSetting(context, Lamp_Preferences.THRESHOLD_ROTATION, 5f)
            Lamp.startRotation(context)//start Sensor
            //Sensor Observer
            Rotation.setSensorObserver {
                val x = it.getAsDouble(Rotation_Provider.Rotation_Data.VALUES_0)
                val y = it.getAsDouble(Rotation_Provider.Rotation_Data.VALUES_1)
                val z = it.getAsDouble(Rotation_Provider.Rotation_Data.VALUES_2)
                //val value=it.
                if (it != null) {
                    val rotationData =
                        RotationData(x, y, z)
                    val data = DimensionData(
                        null,
                        null,
                        null,
                        rotationData,
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
                        null,null,null
                    )
                    val sensorEventData =
                        SensorEventData(
                            data,
                            "lamp.accelerometer.motion",System.currentTimeMillis().toDouble()
                        )
                    LampLog.e("Rotation : $x : $y : $z")

//                    Aware.stopRotation(context)
                    sensorListener.getRotationData(sensorEventData)
                }else{
                    val logEventRequest = LogEventRequest()
                    logEventRequest.message = context.getString(R.string.log_rotation_null)
                    LogUtils.invokeLogData(Utils.getApplicationName(context), context.getString(R.string.warning), logEventRequest)
                }
            }
        }catch (ex : Exception){
            val logEventRequest = LogEventRequest()
            logEventRequest.message = context.getString(R.string.log_rotation_error)
            LogUtils.invokeLogData(Utils.getApplicationName(context), context.getString(R.string.error), logEventRequest)
        }
    }
}