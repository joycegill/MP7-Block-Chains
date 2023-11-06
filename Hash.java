package src;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.lang.*;

/**
 * <Description>
 *
 * @author Joyce Gill
 * @author Maria Rodriguez
 */
public class Hash {
  byte[] data;

  /* Constructs a new Hash object that contains the given hash (as an array of bytes) */
  public Hash(byte[] data) {
    this.data = Arrays.copyOf(data, data.length);
  } // Hash(byte[])

  /* Returns the hash contained in this object. */
  public byte[] getData(){
    return this.data;
  } // getData()

  /* Returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes. */
  public boolean isValid() {
    if (data.length >= 3) {
      if (data[0] == 0 && data[1] == 0 && data[2] == 0) {
        return true;
      } // if
    } // if
    return false;
  } // isValid()

  /* Returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte. */
  @Override
  public String toString() {
    if (this.isValid()) {
      String str = "";
      for (byte b : data) {
        int unsignedInt = Byte.toUnsignedInt(b);
        str.concat(String.format("%2s", unsignedInt));
      } // for
      return str;
    } // if
    return null;
  } // toString()

  /* Returns true if this hash is structurally equal to the argument. */
  public boolean equals(Object other) {
    if (other.getClass() == Hash.class && this.isValid()) {
      Hash o = (Hash) other;
      if (Arrays.equals(this.data, o.getData())) {
        return true;
      } // if
    } // if
    return false;
  } // equals(Object)

} // class Hash