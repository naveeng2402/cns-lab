#include <stdio.h>
#include <math.h>

// Function to calculate (base^exponent) % modulus
long long int power(long long int base, long long int exponent, long long int modulus) {
    long long int result = 1;
    base = base % modulus;
    while (exponent > 0) {
        if (exponent % 2 == 1)
            result = (result * base) % modulus;
        exponent = exponent >> 1; // equivalent to exponent /= 2
        base = (base * base) % modulus;
    }
    return result;
}

// Function to perform Diffie-Hellman key exchange
void diffieHellmanKeyExchange(long long int p, long long int g, long long int privateA, long long int privateB) {
    // Calculate public keys
    long long int publicA = power(g, privateA, p);
    long long int publicB = power(g, privateB, p);

    // Calculate shared secret key
    long long int secretKeyA = power(publicB, privateA, p);
    long long int secretKeyB = power(publicA, privateB, p);

    // Print the results
    printf("Public Key of A: %lld\n", publicA);
    printf("Public Key of B: %lld\n", publicB);
    printf("Secret Key of A: %lld\n", secretKeyA);
    printf("Secret Key of B: %lld\n", secretKeyB);
}

int main() {
    long long int p, g, privateA, privateB;

    // Commonly agreed prime modulus (large prime number)
    printf("Enter prime modulus (p): ");
    scanf("%lld", &p);

    // Commonly agreed base (primitive root modulo p)
    printf("Enter base (g): ");
    scanf("%lld", &g);

    // Private key for party A
    printf("Enter private key of A: ");
    scanf("%lld", &privateA);

    // Private key for party B
    printf("Enter private key of B: ");
    scanf("%lld", &privateB);

    // Perform Diffie-Hellman key exchange
    diffieHellmanKeyExchange(p, g, privateA, privateB);

    return 0;
}
