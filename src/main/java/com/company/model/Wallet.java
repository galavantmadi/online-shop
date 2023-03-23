package com.company.model;

public class Wallet {

    private WalletType walletType;
    private long balance;

    public Wallet(WalletType walletType, long balance) {
        this.walletType = walletType;
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
