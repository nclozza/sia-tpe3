package com.sia.tp3.grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Graficador extends JFrame {

    public void initUI(final List<Punto2D> lista, final String nombre, final String titulo, final String x,
                       final String y) {

        XYDataset dataset = createDataset(lista, nombre);
        JFreeChart chart = createChart(dataset, titulo, x, y);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle(titulo);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset(final List<Punto2D> lista, final String nombre) {

        XYSeries series = new XYSeries(nombre);

        for (Punto2D punto2D : lista) {
            series.add(punto2D.getX(), punto2D.getY());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset, final String titulo, final String x, final String y) {

        JFreeChart chart = ChartFactory.createXYLineChart(titulo, x, y, dataset, PlotOrientation.VERTICAL, true, true
                , false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(titulo, new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;
    }

    public static void mostrarGraficoMejoresDesempenios(LinkedList<Punto2D> mejoresDesempenios) {
        SwingUtilities.invokeLater(() -> {
            Graficador graficador = new Graficador();
            graficador.initUI(mejoresDesempenios, "Desempeño", "Mejor desempeño", "Generacion", "Desempeño");
            graficador.setVisible(true);
        });
    }

    public static void mostrarGraficoPeoresDesempenios(LinkedList<Punto2D> peoresDesempenios) {
        SwingUtilities.invokeLater(() -> {
            Graficador graficador = new Graficador();
            graficador.initUI(peoresDesempenios, "Desempeño", "Peor desempeño", "Generacion", "Desempeño");
            graficador.setVisible(true);
        });
    }

    public static void mostrarGraficoPromedioDesempenios(LinkedList<Punto2D> promedioDesempenios) {
        SwingUtilities.invokeLater(() -> {
            Graficador graficador = new Graficador();
            graficador.initUI(promedioDesempenios, "Desempeño", "Desempeño promedio", "Generacion", "Desempeño");
            graficador.setVisible(true);
        });
    }
}
