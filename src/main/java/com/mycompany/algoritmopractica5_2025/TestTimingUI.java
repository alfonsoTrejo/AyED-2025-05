package com.mycompany.algoritmopractica5_2025;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestTimingUI extends JFrame {
    private static final String CSV_PATH = "C:\\Users\\trejo\\OneDrive\\Documentos\\NetBeansProjects\\Algoritmopractica5_2025\\src\\main\\java\\com\\mycompany\\algoritmopractica5_2025\\weatherHistory.csv";

    private JComboBox<String> columnDropdown;
    private JButton runButton;
    private JButton showCsvButton;
    private JButton viewSortedButton;
    private ChartPanel chartPanel;
    private Map<String, double[]> columns;
    private CSVColumnar csvData;
    private Map<String, Double[]> sortedResults = new LinkedHashMap<>();

    public TestTimingUI() {
        setTitle("Comparador de Sorts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        loadWeatherData();

        JPanel control = new JPanel();
        columnDropdown = new JComboBox<>(columns.keySet().toArray(new String[0]));
        runButton = new JButton("Ejecutar Sorts");
        showCsvButton = new JButton("Ver CSV");
        viewSortedButton = new JButton("Ver Sorted");
        control.add(new JLabel("Columna:"));
        control.add(columnDropdown);
        control.add(runButton);
        control.add(showCsvButton);
        control.add(viewSortedButton);
        add(control, BorderLayout.NORTH);

        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        runButton.addActionListener(e -> runSortAndPlot());
        showCsvButton.addActionListener(e -> showCsvModal());
        viewSortedButton.addActionListener(e -> showOneSortedModal());

        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private void loadWeatherData() {
        try {
            csvData = new CSVColumnar(CSV_PATH);
            WeatherData wd = new WeatherData(csvData);
            columns = new LinkedHashMap<>();
            columns.put("Temperatura (C)", wd.getTemperatures());
            columns.put("Temperatura Aparente (C)", wd.getApparentTemps());
            columns.put("Humedad", wd.getHumidity());
            columns.put("Visibilidad (km)", wd.getVisibility());
            columns.put("Cobertura de Nubes", wd.getCloudCover());
            columns.put("Presión (milibares)", wd.getPressure());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error cargando el CSV: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void runSortAndPlot() {
        String selected = (String) columnDropdown.getSelectedItem();
        double[] base = columns.get(selected);
        Double[] boxed = Arrays.stream(base).boxed().toArray(Double[]::new);

        String[] algos = {"QuickSort","SelectionSort","ShellSort","MergeSort","Arrays.sort","Arrays.parallelSort"};
        long[] times = new long[algos.length];
        sortedResults.clear();

        for (int i = 0; i < algos.length; i++) {
            Double[] a = boxed.clone();
            long t0 = System.nanoTime();
            try {
                switch (i) {
                    case 0 -> algoritmosSorts.quickSort(a);
                    case 1 -> algoritmosSorts.selectionSort(a);
                    case 2 -> algoritmosSorts.shellSort(a);
                    case 3 -> algoritmosSorts.mergeSort(a);
                    case 4 -> Arrays.sort(a);
                    case 5 -> Arrays.parallelSort(a);
                }
                times[i] = System.nanoTime() - t0;
                sortedResults.put(algos[i], a);
            } catch (Exception ex) {
                times[i] = -1;
            }
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < algos.length; i++) {
            if (times[i] >= 0) {
                dataset.addValue(times[i], selected, algos[i]);
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Tiempo para " + selected,
            "Algoritmo",
            "Tiempo (ns)",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        chartPanel.setChart(barChart);
        showSortedCsvModal(selected);
    }

    private void showCsvModal() {
        List<String> headers = csvData.getHeaders();
        List<String[]> rows = csvData.getRows();
        DefaultTableModel model = new DefaultTableModel();
        for (String h : headers) model.addColumn(h);
        for (String[] r : rows) model.addRow(r);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(800, 400));
        JOptionPane.showMessageDialog(this, scroll, "Contenido CSV", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showSortedCsvModal(String column) {
        List<String> headers = csvData.getHeaders();
        List<String[]> rows = csvData.getRows();
        int colIndex = headers.indexOf(column);
        List<String[]> sortedRows = new ArrayList<>(rows);
        sortedRows.sort(Comparator.comparingDouble(r -> Double.parseDouble(r[colIndex])));
        DefaultTableModel model = new DefaultTableModel();
        for (String h : headers) model.addColumn(h);
        for (String[] r : sortedRows) model.addRow(r);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(800, 400));
        JOptionPane.showMessageDialog(this, scroll, "CSV ordenado por " + column, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showOneSortedModal() {
        if (sortedResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero ejecuta los sorts", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] keys = sortedResults.keySet().toArray(new String[0]);
        String choice = (String) JOptionPane.showInputDialog(
            this,
            "Elegir algoritmo:",
            "Ver Sorted",
            JOptionPane.PLAIN_MESSAGE,
            null,
            keys,
            keys[0]
        );
        if (choice == null) return;
        Double[] arr = sortedResults.get(choice);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Índice");
        model.addColumn("Valor");
        for (int i = 0; i < arr.length; i++) {
            model.addRow(new Object[]{i, arr[i]});
        }
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(400, 500));
        JOptionPane.showMessageDialog(this, scroll, "Array ordenado por " + choice, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestTimingUI().setVisible(true));
    }
}
