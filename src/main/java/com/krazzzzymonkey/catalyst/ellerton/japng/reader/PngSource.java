package com.krazzzzymonkey.catalyst.ellerton.japng.reader;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The API for reading any PNG source.
 * @see PngAtOnceSource
 * <p>
 * WARNING: this may be removed in favour of direct use of InputStream objects.
 */
public interface PngSource {
    int getLength();

    boolean supportsByteAccess();

    byte[] getBytes() throws IOException;

    byte readByte() throws IOException;

    short readUnsignedShort() throws IOException;

    int readInt() throws IOException;

    long skip(int chunkLength) throws IOException;

    int tell();

    int available();

//    String getSourceDescription();

//    ByteArrayInputStream getBis();

    DataInputStream getDis();

    InputStream slice(int dataLength) throws IOException;
}
