import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.lang.*;

/**
  * An implementation of Hash
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
    return Arrays.copyOf(this.data, this.data.length);
  } // getData()

  /* Returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes. */
  public boolean isValid() {
    if (data.length >= 3) { // legnth must be minimum 3
      if (data[0] == 0 && data[1] == 0 && data[2] == 0) {
        return true;
      } // if
    } // if
    return false; // return false otherwise
  } // isValid()

  /* Returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte. */
  @Override
  public String toString() {
    String str = "";
    for (byte b : data) {
      int unsignedInt = Byte.toUnsignedInt(b);
      str.concat(String.format("%2s", unsignedInt));
    } // for
    return str;
  } // toString()

  /* Returns true if this hash is structurally equal to the argument. */
  public boolean equals(Object other) {
    if (other.getClass() == Hash.class) {
      Hash o = (Hash) other;
      if (Arrays.equals(this.data, o.getData())) {
        return true;
      } // if
    } // if
    return false;
  } // equals(Object)

} // class Hash
