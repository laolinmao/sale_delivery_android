package com.histudio.ui.calendar;

/**
 * Created by codbking on 2016/12/23.
 * email:codbking@gmail.com
 * github:https://github.com/codbking
 * blog:http://www.jianshu.com/users/49d47538a2dd/latest_articles
 */

public interface CalendarTopView {

    int[] getCurrentSelectPosition();

    int getItemHeight();

    void setCalendarTopViewChangeListener(CalendarTopViewChangeListener listener);

}
