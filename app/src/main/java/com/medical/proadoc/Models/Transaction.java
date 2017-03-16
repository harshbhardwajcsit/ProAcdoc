package com.medical.proadoc.Models;

/**
 * Created by intel on 11/4/2015.
 */
public class Transaction {
    String TransactionMessage;
    int TransactionStatus;

    public String getTransactionMessage()
    {
        return this.TransactionMessage;
    }

    public int getTransactionStatus()
    {
        return this.TransactionStatus;
    }

    public Transaction gettransacctionDetails(String paramString, int paramInt)
    {
        Transaction localTransaction = new Transaction();
        localTransaction.setTransactionMessage(paramString);
        localTransaction.setTransactionStatus(paramInt);
        return localTransaction;
    }

    public void setTransactionMessage(String paramString)
    {
        this.TransactionMessage = paramString;
    }

    public void setTransactionStatus(int paramInt)
    {
        this.TransactionStatus = paramInt;
    }

}
