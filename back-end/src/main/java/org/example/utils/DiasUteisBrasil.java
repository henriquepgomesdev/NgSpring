package org.example.utils;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Arrays;

public class DiasUteisBrasil {

    // Lista de feriados brasileiros em 2025 (adicione os feriados conforme necessário)
    private static List<LocalDate> feriados = Arrays.asList(
            LocalDate.of(2025, 1, 1),  // Ano Novo
            LocalDate.of(2025, 4, 21), // Tiradentes
            LocalDate.of(2025, 5, 1),  // Dia do Trabalho
            LocalDate.of(2025, 9, 7),  // Independência do Brasil
            LocalDate.of(2025, 10, 12), // Nossa Senhora Aparecida
            LocalDate.of(2025, 11, 15), // Proclamação da República
            LocalDate.of(2025, 12, 25)  // Natal
    );

    // Método para contar os dias úteis no período
    public static long contarDiasUteis(LocalDate dataInicial, LocalDate dataFinal) {
        long diasUteis = 0;
        LocalDate dataAtual = dataInicial;

        while (!dataAtual.isAfter(dataFinal)) {
            // Verificar se o dia atual é um dia útil (não é sábado ou domingo e não é feriado)
            if (dataAtual.getDayOfWeek() != DayOfWeek.SATURDAY && dataAtual.getDayOfWeek() != DayOfWeek.SUNDAY
                    && !isFeriado(dataAtual)) {
                diasUteis++;
            }
            dataAtual = dataAtual.plusDays(1);
        }

        return diasUteis;
    }

    public static boolean isDiaUtil(LocalDate data) {
        return data.getDayOfWeek() != DayOfWeek.SATURDAY &&
                data.getDayOfWeek() != DayOfWeek.SUNDAY &&
                !isFeriado(data);
    }

    // Verificar se a data é feriado
    private static boolean isFeriado(LocalDate data) {
        return feriados.contains(data);
    }

}
