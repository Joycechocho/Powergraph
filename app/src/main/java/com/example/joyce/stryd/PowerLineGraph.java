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

public class PowerLineGraph
{
    private GraphicalView view;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private TimeSeries dataset = new TimeSeries("total_power_list");

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeriesRenderer renderer = new XYSeriesRenderer();

    public PowerLineGraph()
    {
        mDataset.addSeries(dataset);//add single dataset to multiple dataset

        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        mRenderer.setZoomButtonsVisible(true); //enable zoom

        mRenderer.setXTitle("Time (Second)");
        mRenderer.setYTitle("Power (W)");
        mRenderer.setAxisTitleTextSize(50);
        mRenderer.setLabelsColor(Color.YELLOW);
        mRenderer.setChartTitle("Total Power List");
        mRenderer.setYAxisMin(0);
        mRenderer.setYLabels(50);

        mRenderer.setXAxisMax(6);
        mRenderer.setXAxisMin(0);

        mRenderer.setShowGrid(true);
        renderer.setLineWidth(2);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing(10);
        renderer.setChartValuesTextSize(40);

        mRenderer.addSeriesRenderer(renderer);//add single renderer to multiple renderer
    }


    public GraphicalView getpowerview(Context context)
    {
        view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
        return view;
    }

    public void addPowerPoints(Point p)
    {
        dataset.add(p.getX(), p.getY());
    }

}
