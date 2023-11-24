#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void encrypt(char *text, int shift) {
    // Iterate through each character in the string
  for (int i = 0; text[i] != '\0'; ++i) {
    // Check if the character is an uppercase letter
    if (text[i] >= 'A' && text[i] <= 'Z') {
      // Apply the shift to the uppercase letter
      text[i] = (text[i] - 'A' + shift + 26) % 26 + 'A';
    }
    // Check if the character is a lowercase letter
    else if (text[i] >= 'a' && text[i] <= 'z') {
      // Apply the shift to the lowercase letter
      text[i] = (text[i] - 'a' + shift + 26) % 26 + 'a';
    }
    // Ignore non-alphabetic characters
  }
}

void decrypt(char *text, int shift) {
    // The decryption is the same as encryption, but with a negative shift
    encrypt(text, -shift);
}

int main()
{
  char msg[100];
  int key;

  printf("Enter a message to encrypt: ");
  fgets(msg, 100, stdin);
  printf("Enter key: ");
  scanf("%d", &key);

  key = key % 26;

  if(strlen(msg) < 3 || strlen(msg) > 100){
    printf("Error! Enter a message between 3 and 100 characters.");
    exit(1);
  }

  // remove the trailing newline from the message
  msg[strcspn(msg, "\n")] = 0;

  encrypt(msg, key);
  printf("Encrypted message: %s\n", msg);

  decrypt(msg, key);
  printf("Decrypted message: %s\n", msg);

  return 0;
}