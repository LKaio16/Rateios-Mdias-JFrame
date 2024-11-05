package com.mdiasbranco.processador;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessadorService {
    private static final String UPLOAD_DIR = "TEMP_DIR/";
    private static final String OUTPUT_FILE = "TEMP_DIR/output_mdiasbranco.txt";

    public Resource processarArquivo(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Arquivo não encontrado ou inválido.");
        }

        // Cria diretório temporário, se necessário
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Verifica se o arquivo zip_mdiasbranco.zip já existe e o deleta antes de copiar o novo
        Path path = Paths.get(UPLOAD_DIR + "zip_mdiasbranco.zip");
        if (Files.exists(path)) {
            Files.delete(path);
        }

        // Copia o arquivo ZIP para o diretório temporário
        Files.copy(file.toPath(), path);

        // Executa o processamento do arquivo
        ZipProcessor.main(null);

        // Prepara o arquivo de saída para download
        File fileResult = new File(OUTPUT_FILE);
        if (!fileResult.exists()) {
            throw new IOException("Erro ao gerar o arquivo de saída.");
        }

        Path filePath = Paths.get(fileResult.getAbsolutePath());
        byte[] fileBytes = Files.readAllBytes(filePath);

        // Retorna o arquivo processado sem deletar o diretório
        System.out.println("Arquivo processado com sucesso.");

        return new ByteArrayResource(fileBytes);
    }


    private void deleteDirectory(File directory) throws IOException {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            Files.delete(directory.toPath());
        }
    }
}
