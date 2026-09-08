# Text Encryptor/Decryptor (Hill Cipher)

A Java program that reads text from a file, encrypts it using a **Hill cipher** (2x2 matrix-based encryption), writes the encrypted result to a file, then reads it back and decrypts it to recover the original message.

## How It Works

1. Reads input text from `msg.txt`
2. Filters the text to keep only **lowercase letters** (a–z), converting uppercase to lowercase first
3. Converts each letter to a number (a=1, b=2, ..., z=26)
4. Encrypts the numbers two at a time using 2x2 matrix multiplication with a fixed encryption key, writing the result to `EncryptedData.txt`
5. Reads the encrypted file back in and decrypts it using the corresponding decryption key
6. Writes the final decrypted text to `result_message.txt`

## Files

| File | Description |
|------|-------------|
| `Main.java` | Entry point — controls the overall encryption/decryption flow and file handling |
| `Data.java` | Contains `Data` (abstract base class), `FileData` (reads a file into a String), and `MyLinkedList` (converts text into a LinkedList of characters and writes output) |
| `Algorithm.java` | Contains `Algorithm` (abstract base class with cipher keys and char↔num conversion), `Encryptor`, and `Decryptor` |
| `msg.txt` | Sample input file — place your text here before running |

## How to Run

1. Make sure `msg.txt` is in the same folder as the `.java` files
2. Compile: `javac Main.java Data.java Algorithm.java`
3. Run: `java Main`
4. Check the generated `EncryptedData.txt` and `result_message.txt` for output

## Behavior & Limitations

- **Only lowercase letters (a–z) are processed.** The program automatically lowercases the input, but any character that isn't a letter — including numbers, punctuation, and spaces — is stripped out before encryption. If you provide a mix of letters and numbers, only the letters will appear in the output.
- **Spaces are not supported.** Multi-word input will be encrypted/decrypted as one continuous run of letters with no spacing preserved.
- **Empty input file:** the program detects this and prints/writes `"File is empty."` instead of attempting to encrypt.
- **Invalid input (non-alphabetic only, e.g. all numbers or symbols):** the program detects that no valid letters remain and prints/writes `"No valid data provided."`
- **Missing input file:** if `message.txt` doesn't exist, the program prints/writes `"Input file not found."`
- **Odd-length input** is automatically padded with a null character before encryption, and the padding is removed again after decryption, so the final decrypted text matches the original letter count.
