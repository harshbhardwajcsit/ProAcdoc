package com.medical.proadoc.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.medical.proadoc.R;

import java.util.ArrayList;

public class ChartOne extends Fragment {


    public ChartOne() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_one, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {


        LoadFirstChart();
        LoadSecondChart();

        LoadThirdChart();
        super.onActivityCreated(savedInstanceState);
    }public void LoadFirstChart()
    {
        BarChart chart = (BarChart)getView(). findViewById(R.id.chartfirst);

        BarData data = new BarData(getFirstXAxisValues(), getFirstDataSet());
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // to set xAxis in Bottom
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }
    private ArrayList<BarDataSet> getFirstDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);


        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Doctor Visits In Last 5 Year");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<BarDataSet>();

        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getFirstXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("Head");
        xAxis.add("Hand");
        xAxis.add("Skin");
        xAxis.add("Leg");
        xAxis.add("Hair");
        xAxis.add("Others");
        return xAxis;
    }


    public void LoadSecondChart()
    {
        BarChart chart = (BarChart)getView(). findViewById(R.id.chartsecond);

        BarData data = new BarData(getSecondXAxisValues(), getSecondDataSet());
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // to set xAxis in Bottom
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }







    private ArrayList<String> getSecondXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("2011");
        xAxis.add("2012");
        xAxis.add("2013");
        xAxis.add("2014");
        xAxis.add("2015");
        xAxis.add("2016");
        return xAxis;
    }
    private ArrayList<BarDataSet> getSecondDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        BarEntry v1e1 = new BarEntry(7000.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(1000.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(2000.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(10300.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(22000.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        BarEntry v2e1 = new BarEntry(50000.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(16000.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(21000.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(54000.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(2355.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(7000.000f, 5); // Jun
        valueSet2.add(v2e6);


        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Medical Expense Trending");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<BarDataSet>();

        dataSets.add(barDataSet2);
        return dataSets;
    }

    public void LoadThirdChart()
    {
        BarChart chart = (BarChart)getView(). findViewById(R.id.chartthree);

        BarData data = new BarData(getThirdXAxisValues(), geThirdDataSet());
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // to set xAxis in Bottom
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }


    private ArrayList<String> getThirdXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("Knee-2011");
        xAxis.add("Heart-2012");
        xAxis.add("Skin-2013");
        xAxis.add("Nose-2014");
        xAxis.add("Skin-2015");
        xAxis.add("Others-2016");
        return xAxis;
    }
    private ArrayList<BarDataSet> geThirdDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        BarEntry v1e1 = new BarEntry(7000.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(1000.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(2000.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(10300.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(22000.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        BarEntry v2e1 = new BarEntry(50000.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(16000.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(21000.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(54000.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(2355.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(7000.000f, 5); // Jun
        valueSet2.add(v2e6);


        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Ailmentwise Expense Trendings");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<BarDataSet>();

        dataSets.add(barDataSet2);
        return dataSets;
    }


}
