package com.amrendra.codefiesta.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amrendra.codefiesta.R;
import com.amrendra.codefiesta.db.DBContract;
import com.amrendra.codefiesta.utils.AppUtils;
import com.amrendra.codefiesta.utils.CustomDate;
import com.amrendra.codefiesta.utils.DateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Amrendra Kumar on 10/04/16.
 */
public class ContestAdapter extends CursorAdapter {

    final LayoutInflater mInflator;
    Context mContext;

    public ContestAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflator = LayoutInflater.from(context);
        mContext = context;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View mItem = mInflator.inflate(R.layout.contest_list_item, parent, false);
        ViewHolder mHolder = new ViewHolder(mItem);
        mItem.setTag(mHolder);
        return mItem;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder mHolder = (ViewHolder) view.getTag();


        mHolder.contestTitleTv.setText(cursor.getString(cursor.getColumnIndex(DBContract
                .ContestEntry.CONTEST_NAME_COL)));
        int resourceId = cursor.getInt(cursor.getColumnIndex(DBContract
                .ContestEntry.CONTEST_RESOURCE_ID_COL));
        mHolder.contestWebsiteTv.setText(AppUtils.getGoodResourceName(AppUtils.getResourceName(context, resourceId)));
        long duration = cursor.getLong(cursor.getColumnIndex(DBContract
                .ContestEntry.CONTEST_DURATION_COL));
        //mHolder.contestDurationTv.setText(DateUtils.getDurationString(duration, false));
        long starTime = Long.parseLong(cursor.getString(cursor.getColumnIndex(DBContract
                .ContestEntry.CONTEST_START_COL)));
        long endTime = Long.parseLong(cursor.getString(cursor.getColumnIndex(DBContract
                .ContestEntry.CONTEST_END_COL)));
        CustomDate startDate = new CustomDate(DateUtils.epochToDateTimeLocalShow(starTime));
        CustomDate endDate = new CustomDate(DateUtils.epochToDateTimeLocalShow(endTime));
        mHolder.contestStartTime.setText(startDate.getTime());
        mHolder.contestStartAmPm.setText(startDate.getAmPm());
        mHolder.contestStartDate.setText(startDate.getDateMonthYear());

        mHolder.contestEndTime.setText(endDate.getTime());
        mHolder.contestEndAmPm.setText(endDate.getAmPm());
        mHolder.contestEndDate.setText(endDate.getDateMonthYear());

        mHolder.statusTv.setText(DateUtils.getTimeLeftForEnd(starTime, endTime));
    }

    class ViewHolder {
        @Bind(R.id.contest_title_tv)
        TextView contestTitleTv;
        @Bind(R.id.contest_website_tv)
        TextView contestWebsiteTv;
        /*     @Bind(R.id.contest_duration_tv)
            TextView contestDurationTv;*/
        @Bind(R.id.status_tv)
        TextView statusTv;

        @Bind(R.id.contest_start_time_tv)
        TextView contestStartTime;
        @Bind(R.id.contest_start_time_ampm)
        TextView contestStartAmPm;
        @Bind(R.id.contest_start_date_tv)
        TextView contestStartDate;

        @Bind(R.id.contest_end_time_tv)
        TextView contestEndTime;
        @Bind(R.id.contest_end_time_ampm)
        TextView contestEndAmPm;
        @Bind(R.id.contest_end_date_tv)
        TextView contestEndDate;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
