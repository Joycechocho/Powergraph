package com.example.joyce.stryd;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class HRLineGraph
{
    private GraphicalView view;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private TimeSeries dataset = new TimeSeries("heart_rate_list");

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeriesRenderer renderer = new XYSeriesRenderer();

    public HRLineGraph()
    {
        mDataset.addSeries(dataset);

        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        mRenderer.setZoomButtonsVisible(true);

        mRenderer.setXTitle("Time (Second)");
        mRenderer.setYTitle("Heart Rate (Beat)");
        mRenderer.setAxisTitleTextSize(50);
        mRenderer.setLabelsColor(Color.YELLOW);
        mRenderer.setChartTitle("Heart Rate List");
        mRenderer.setYAxisMin(0);
        mRenderer.setYLabels(50);

        mRenderer.setXAxisMax(6);
        mRenderer.setXAxisMin(0);

        mRenderer.setShowGrid(true);
        renderer.setLineWidth(2);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing(10);
        renderer.setChartValuesTextSize(40);

        mRenderer.addSeriesRenderer(renderer);
    }


    public GraphicalView getheartrateview(Context context){
        view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
        return view;
    }

    public void addHeartRatePoints(Point p){
        dataset.add(p.getX(), p.getY());
    }
}
