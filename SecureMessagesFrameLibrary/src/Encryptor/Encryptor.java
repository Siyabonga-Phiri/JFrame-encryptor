/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

/**
 *
 * @author user
 */
public class Encryptor {
    
    private String message;

    public Encryptor(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
  public String encryptMessage() {
    String encrypted = "";
    char stepMessage;

    char[] letters = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    for (int i = 0; i < message.length(); i++) {
        stepMessage = message.charAt(i);

        if (Character.isLetter(stepMessage)) {
            stepMessage = Character.toUpperCase(stepMessage); // Convert to uppercase

            for (int x = 0; x < letters.length; x++) {
                if (stepMessage == letters[x]) {
                    x += 3; // Shift the letter by 3 positions

                    if (x > 25) {
                        x -= 26; // Wrap around if beyond 'Z'
                    }

                    stepMessage = letters[x];
                    break; // Exit the loop once the letter is found and replaced
                }
            }
        }

        encrypted += stepMessage; // Append the encrypted character to the result
    }

    return encrypted;
}
}