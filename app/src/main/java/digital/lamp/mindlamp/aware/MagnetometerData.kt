package digital.lamp.mindlamp.aware

import android.content.Context
import com.aware.Aware
import com.aware.Aware_Preferences
import com.aware.Rotation
import com.aware.providers.Rotation_Provider
import digital.lamp.mindlamp.R
import digital.lamp.mindlamp.appstate.AppState
import digital.lamp.mindlamp.network.model.*
import digital.lamp.mindlamp.utils.Utils

/**
 * Created by ZCO Engineering Dept. on 06,February,2020
 */
class MagnetometerData constructor(awareListener: AwareListener, context: Context){
    init {
        try {
            //Rotation Sensor Settings
            Aware.setSetting(
                context,
                Aware_Preferences.FREQUENCY_ROTATION,
                200000
            ) //20Hz
            Aware.setSetting(context, Aware_Preferences.THRESHOLD_ROTATION, 0.02f)
            Aware.startMagnetometer(context)//start Sensor
            //Sensor Observer
            Rotation.setSensorObserver {
                val x = it.getAsDouble(Rotation_Provider.Rotation_Data.VALUES_0)
                val y = it.getAsDouble(Rotation_Provider.Rotation_Data.VALUES_1)
                val z = it.getAsDouble(Rotation_Provider.Rotation_Data.VALUES_2)
                //val value=it.
                if (it != null) {
                    val magnetData =
                        MagnetData(x, y, z)
                    val data = DimensionData(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        magnetData,
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
                            "lamp.accelerometer.motion",System.currentTimeMillis()
                        )
                    Aware.startMagnetometer(context)
                    awareListener.getMagneticData(sensorEventData)
                }else{
                    val logEventRequest = LogEventRequest(context.getString(R.string.log_magnetometer_null), UserAgent(), AppState.session.userId)
                    LogUtils.invokeLogData(Utils.getApplicationName(context), context.getString(R.string.warning), logEventRequest)
                }
            }
        }catch (ex : Exception){
            val logEventRequest = LogEventRequest(context.getString(R.string.log_magnetometer_error), UserAgent(), AppState.session.userId)
            LogUtils.invokeLogData(Utils.getApplicationName(context), context.getString(R.string.error), logEventRequest)
        }
    }
}