#include <math.h>
#include <stdio.h>

// Function to calculate (base^exponent) % modulus
long long int power(long long int base, long long int exponent,
                    long long int modulus) {
  long long int result = 1;
  base = base % modulus;
  while (exponent > 0) {
    if (exponent % 2 == 1) result = (result * base) % modulus;
    exponent = exponent >> 1;  // equivalent to exponent /= 2
    base = (base * base) % modulus;
  }
  return result;
}

// Function to find the greatest common divisor (GCD) of two numbers
long long int gcd(long long int a, long long int b) {
  if (b == 0) return a;
  return gcd(b, a % b);
}

// Function to generate public and private keys for RSA
void generateKeys(long long int p, long long int q, long long int *publicKey,
                  long long int *privateKey) {
  long long int n = p * q;
  long long int phi = (p - 1) * (q - 1);

  // Choose e such that 1 < e < phi and gcd(e, phi) = 1
  long long int e;
  for (e = 2; e < phi; e++) {
    if (gcd(e, phi) == 1) break;
  }

  // Calculate d, the modular multiplicative inverse of e (mod phi)
  long long int d;
  for (d = 2; d < phi; d++) {
    if ((e * d) % phi == 1) break;
  }

  *publicKey = e;
  *privateKey = d;
}

// Function to perform RSA encryption
long long int rsaEncrypt(long long int message, long long int publicKey,
                         long long int n) {
  return power(message, publicKey, n);
}

// Function to perform RSA decryption
long long int rsaDecrypt(long long int encryptedMessage,
                         long long int privateKey, long long int n) {
  return power(encryptedMessage, privateKey, n);
}

int main() {
  long long int p, q, publicKey, privateKey, n, message, encryptedMessage,
      decryptedMessage;

  // Choose two prime numbers
  printf("Enter prime number p: ");
  scanf("%lld", &p);
  printf("Enter prime number q: ");
  scanf("%lld", &q);

  // Generate public and private keys
  generateKeys(p, q, &publicKey, &privateKey);

  // Calculate n
  n = p * q;

  // Input message to be encrypted
  printf("Enter message to be encrypted (less than %lld): ", n);
  scanf("%lld", &message);

  // Perform encryption
  encryptedMessage = rsaEncrypt(message, publicKey, n);

  // Perform decryption
  decryptedMessage = rsaDecrypt(encryptedMessage, privateKey, n);

  // Print results
  printf("Public Key (e, n): (%lld, %lld)\n", publicKey, n);
  printf("Private Key (d, n): (%lld, %lld)\n", privateKey, n);
  printf("Original Message: %lld\n", message);
  printf("Encrypted Message: %lld\n", encryptedMessage);
  printf("Decrypted Message: %lld\n", decryptedMessage);

  return 0;
}
