import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Declaramos la clase Menu_convertidor que hara uso de JFrame para que podamos trabajar la interfaz grafica de nuestro
 * conversor
 */
public class Menu_convertidor extends JFrame {
    private JTextField amountField;
    private JLabel resultLabel;
    private JComboBox<String> conversionOptions;

    /**
     * Declaración del tamaño de la interfaz
     */
    public Menu_convertidor() {
        setTitle("Convertidor de monedas");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Monto:");
        amountField = new JTextField(10);

        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        /**
         * En esta sección declaramos las opciones para convertir diferentes tipos de monedas
         */
        conversionOptions = new JComboBox<>(new String[]{
                "USD a EUR",
                "EUR a USD",
                "USD a JPY",
                "JPY a USD",
                "USD a GBP",
                "GBP a USD",
                "USD a MXN",
                "MXN a USD",
        });

        inputPanel.add(conversionOptions);

        JButton convertButton = new JButton("Convertir");
        convertButton.addActionListener(new ConvertButtonListener());

        inputPanel.add(convertButton);

        add(inputPanel, BorderLayout.NORTH);

        resultLabel = new JLabel("Valor: ");
        add(resultLabel, BorderLayout.CENTER);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                CurrencyConverter converter = getConverter(conversionOptions.getSelectedItem().toString());
                double resultado = converter.convert(amount);
                resultLabel.setText("Valor: " + amount + " " + converter.getSourceCurrency() + " = " + resultado + " " + converter.getTargetCurrency());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Monto invalido");
            } catch (Exception ex) {
                resultLabel.setText("Error");
            }
        }
    }

    /**
     *
     * @param conversionType hace alusión al tipo de cambio que deseamos saber
     * @return
     */
    private CurrencyConverter getConverter(String conversionType) {
        return switch (conversionType) {
            case "USD a EUR" -> new USDaEUR();
            case "EUR a USD" -> new EURaUSD();
            case "USD a JPY" -> new USDaJPY();
            case "JPY a USD" -> new JPYaUSD();
            case "USD a MXN" -> new USDaMXN();
            case "MXN a USD" -> new MXNaUSD();
            default -> throw new IllegalArgumentException("Tipo desconocido");
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu_convertidor app = new Menu_convertidor();
            app.setVisible(true);
        });
    }
}
