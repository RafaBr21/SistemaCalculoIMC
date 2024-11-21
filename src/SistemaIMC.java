import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaIMC extends JFrame {

    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel labelResultado;
    private JLabel labelImagem;

    private JPanel painelImagem;

    public SistemaIMC() {

        setTitle("Calculo do IMC");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelPeso = new JLabel("Peso (kg): ");
        labelPeso.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelEntrada.add(labelPeso, gbc);

        campoPeso = new JTextField(10);
        campoPeso.setFont(new Font("Arial", Font.BOLD, 20));
        campoPeso.setForeground(Color.GRAY);
        gbc.gridx = 1;
        painelEntrada.add(campoPeso, gbc);

        JLabel labelAltura = new JLabel("Altura (m): ");
        labelAltura.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelEntrada.add(labelAltura, gbc);

        campoAltura = new JTextField(10);
        campoAltura.setFont(new Font("Arial", Font.BOLD, 20));
        campoAltura.setForeground(Color.GRAY);
        gbc.gridx = 1;
        painelEntrada.add(campoAltura, gbc);

        add(painelEntrada, BorderLayout.NORTH);

        JPanel painelOperacao = new JPanel();
        JButton botaoCalcular = new JButton("Calcular o IMC");
        estilizarBotao(botaoCalcular);
        painelOperacao.add(botaoCalcular);
        add(painelOperacao, BorderLayout.CENTER);

        labelResultado = new JLabel("Resultado: ");
        labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
        labelResultado.setFont(new Font("Arial", Font.PLAIN, 26));
        add(labelResultado, BorderLayout.SOUTH);

        painelImagem = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(painelImagem, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        labelImagem = new JLabel();
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelImagem.add(labelImagem, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.EAST);

        botaoCalcular.addActionListener(new CalcularIMCListener());

        setVisible(true);
    }

    private void estilizarBotao(JButton botao) {
        botao.setFont(new Font("Arial", Font.BOLD, 23));
        botao.setBackground(new Color(30, 180, 115));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(40, 20, 190), 1, true));

    }

    private class CalcularIMCListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double peso = Double.parseDouble(campoPeso.getText());
                double altura = Double.parseDouble(campoAltura.getText());

                if (peso <= 0 || altura <= 0) {
                    labelResultado.setText("Erro: peso e altura devem ser maiores que 0");
                    labelImagem.setIcon(null);
                    return;
                }

                double imc = peso / (altura * altura);
                String categoria = "";
                String imagemPath = "";

                if (imc < 18.5) {
                    categoria = "Abaixo do peso";
                    imagemPath = "imagens/magro.png";
                } else if (imc < 24.9) {
                    categoria = "peso Normal";
                    imagemPath = "imagens/saudavel.png";
                } else if (imc < 29.9) {
                    categoria = "Sobrepeso";
                    imagemPath = "imagens/sobrepeso.png";
                } else {
                    categoria = "Obesidade";
                    imagemPath = "imagens/gordura.png";
                }
                labelResultado.setText(String.format("IMC: %.2f - %s", imc, categoria));

                ImageIcon imagemOriginal = new ImageIcon(imagemPath);
                Image imagemRedirecionada = imagemOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon imagem = new ImageIcon(imagemRedirecionada);
                labelImagem.setIcon(imagem);
            } catch (NumberFormatException ex) {
                labelResultado.setText("Erro: insira números válidos");
            }
        }
    }

    public static void main(String[] args) {
        new SistemaIMC();
    }

}
