/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.legacySupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDateTime;

public class NewJava implements ILegacyCompat
{
    @Override
    public int[] getDate() {
        final int[] ret = { LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getYear() };
        return ret;
    }
    
    @Override
    public String getFormattedDate() {
        final DateTimeFormatter format = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        final LocalDate date = LocalDateTime.now().withDayOfMonth(this.getDate()[1]).withMonth(this.getDate()[0]).withYear(this.getDate()[2]).toLocalDate();
        return date.format(format);
    }
}
