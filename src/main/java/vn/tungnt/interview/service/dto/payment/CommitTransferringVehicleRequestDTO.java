package vn.tungnt.interview.service.dto.payment;

public class CommitTransferringVehicleRequestDTO {

    private long transactionId;

    private long bankAccountId;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final long transactionId) {
        this.transactionId = transactionId;
    }

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(final long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
