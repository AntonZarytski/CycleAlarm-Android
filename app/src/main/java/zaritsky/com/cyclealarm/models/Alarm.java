package zaritsky.com.cyclealarm.models;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Alarm extends BroadcastReceiver implements Serializable{
    private Calendar timeOfActiveCalendar;
    private String name;
    private String note;
    private Cycle datesOfActiveCycle;
    private String nameOfVibroType;
    private String nameOfMusic;
    private String nameOfSmoothMusic;
    private boolean isPause;
    private boolean isMusic;
    private boolean isVibro;
    private boolean isSmoothWakeUp;
    private boolean isScoringOfTime;
    private int longPause;
    private int repeatTimePause;
    private int smoothMusicId;
    private int alarmMusicId;
    private int volumeOfAlarmMusic;
    private int forceOfVibro;
    private boolean isActive;
    private boolean isOn;


    public void setActive(boolean active) {
        isActive = active;
    }

    public Alarm(Calendar timeOfActiveCalendar, String note, Cycle cycle) {
        datesOfActiveCycle = cycle;
        this.timeOfActiveCalendar = timeOfActiveCalendar;
        this.note = note;
        this.isActive = true;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LOG_TAG", "ALARM_IS_ON");
    }

    /**set/get*/
//    public Date getTimeOfActive() {
//        return timeOfActive;
//    }
//
//    public void setTimeOfActive(Date timeOfActive) {
//        this.timeOfActive = timeOfActive;
//    }
    @SuppressLint("NewApi")
    public String getFormatedTime(){
        return new SimpleDateFormat("H:mm",
                new Locale("ru", "RU")).format(timeOfActiveCalendar.getTime());
    }

    public Calendar getTimeOfActiveCalendar() {
        return timeOfActiveCalendar;
    }

    public void setTimeOfActiveCalendar(Calendar timeOfActiveCalendar) {
        this.timeOfActiveCalendar = timeOfActiveCalendar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Cycle getDatesOfActiveCycle() {
        return datesOfActiveCycle;
    }

    public void setDatesOfActiveCycle(Cycle datesOfActiveCycle) {
        this.datesOfActiveCycle = datesOfActiveCycle;
    }

    public boolean isMusic() {
        return isMusic;
    }

    public void setMusic(boolean music) {
        isMusic = music;
    }

    public boolean isVibro() {
        return isVibro;
    }

    public void setVibro(boolean vibro) {
        isVibro = vibro;
    }

    public boolean isSmoothWakeUp() {
        return isSmoothWakeUp;
    }

    public void setSmoothWakeUp(boolean smoothWakeUp) {
        isSmoothWakeUp = smoothWakeUp;
    }

    public boolean isScoringOfTime() {
        return isScoringOfTime;
    }

    public void setScoringOfTime(boolean scoringOfTime) {
        isScoringOfTime = scoringOfTime;
    }

    public int getSmoothMusicId() {
        return smoothMusicId;
    }

    public void setSmoothMusicId(int smoothMusicId) {
        this.smoothMusicId = smoothMusicId;
    }

    public int getAlarmMusicId() {
        return alarmMusicId;
    }

    public void setAlarmMusicId(int alarmMusicId) {
        this.alarmMusicId = alarmMusicId;
    }

    public int getVolumeOfAlarmMusic() {
        return volumeOfAlarmMusic;
    }

    public void setVolumeOfAlarmMusic(int volumeOfAlarmMusic) {
        this.volumeOfAlarmMusic = volumeOfAlarmMusic;
    }

    public int getForceOfVibro() {
        return forceOfVibro;
    }

    public void setForceOfVibro(int forceOfVibro) {
        this.forceOfVibro = forceOfVibro;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public int getLongPause() {
        return longPause;
    }

    public void setLongPause(int longPause) {
        this.longPause = longPause;
    }

    public String getNameOfVibroType() {
        return nameOfVibroType;
    }

    public void setNameOfVibroType(String nameOfVibroType) {
        this.nameOfVibroType = nameOfVibroType;
    }

    public String getNameOfMusic() {
        return nameOfMusic;
    }

    public void setNameOfMusic(String nameOfMusic) {
        this.nameOfMusic = nameOfMusic;
    }

    public String getNameOfSmoothMusic() {
        return nameOfSmoothMusic;
    }

    public void setNameOfSmoothMusic(String nameOfSmoothMusic) {
        this.nameOfSmoothMusic = nameOfSmoothMusic;
    }

    public int getRepeatTimePause() {
        return repeatTimePause;
    }

    public void setRepeatTimePause(int repeatTimePause) {
        this.repeatTimePause = repeatTimePause;
    }
}
