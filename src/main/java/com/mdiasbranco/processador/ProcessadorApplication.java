package com.mdiasbranco.processador;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class ProcessadorApplication {

    private static final String OUTPUT_FILE = "TEMP_DIR/output_mdiasbranco.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Processador MDIAS");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            // Cria o botão de upload
            JButton uploadButton = new JButton("Selecionar Arquivo ZIP");
            uploadButton.addActionListener(e -> selecionarArquivo(frame));

            // Cria um painel com GridBagLayout para centralizar o botão
            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.add(uploadButton); // Adiciona o botão ao painel centralizado

            // Cria uma label para o rodapé
            JLabel footerLabel = new JLabel("Feito por Luis Kaio", SwingConstants.CENTER);

            // Adiciona o painel central e o rodapé ao frame usando BorderLayout
            frame.setLayout(new BorderLayout());
            frame.add(centerPanel, BorderLayout.CENTER); // Centraliza o painel com o botão
            frame.add(footerLabel, BorderLayout.SOUTH);  // Adiciona o rodapé

            frame.setVisible(true);
        });
    }

    private static void selecionarArquivo(JFrame parentFrame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Escolha o arquivo ZIP para processar");

        int result = fileChooser.showOpenDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            processarArquivo(parentFrame, selectedFile);
        }
    }

    private static void processarArquivo(JFrame parentFrame, File zipFile) {
        // Dialog de loading
        JDialog loadingDialog = new JDialog(parentFrame, "Processando...", true);
        JLabel loadingLabel = new JLabel("Processando, por favor aguarde...", SwingConstants.CENTER);
        loadingDialog.add(loadingLabel);
        loadingDialog.setSize(300, 100);
        loadingDialog.setLocationRelativeTo(parentFrame);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                ProcessadorService service = new ProcessadorService();
                try {
                    // Processa o arquivo
                    service.processarArquivo(zipFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(parentFrame, "Erro ao processar o arquivo.");
                }
                return null;
            }

            @Override
            protected void done() {
                // Fecha o diálogo de loading e inicia o processo de salvamento
                loadingDialog.dispose();
                baixarArquivoProcessado(parentFrame);
            }
        };

        worker.execute();
        loadingDialog.setVisible(true); // Mostra o loading enquanto a thread está em execução
    }


    private static void baixarArquivoProcessado(JFrame parentFrame) {
        Path outputPath = Paths.get(OUTPUT_FILE);

        // Verifica se o arquivo de saída existe antes de tentar copiá-lo
        if (!Files.exists(outputPath)) {
            JOptionPane.showMessageDialog(parentFrame, "Erro: O arquivo processado não foi encontrado.");
            return;
        }

        JFileChooser saveFileChooser = new JFileChooser();
        saveFileChooser.setDialogTitle("Salvar arquivo processado");
        saveFileChooser.setSelectedFile(new File("output_mdiasbranco.txt"));

        int userSelection = saveFileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File saveFile = saveFileChooser.getSelectedFile();
            try {
                // Adiciona o REPLACE_EXISTING para substituir o arquivo, se ele já existir
                Files.copy(outputPath, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(parentFrame, "Arquivo salvo com sucesso em: " + saveFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(parentFrame, "Erro ao salvar o arquivo processado.");
            }
        }
    }

}
