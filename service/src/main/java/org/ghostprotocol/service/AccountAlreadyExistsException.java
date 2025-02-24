package org.ghostprotocol.storage;

class AccountAlreadyExistsException extends Exception {
  private final Account existingAccount;

  public AccountAlreadyExistsException(final Account existingAccount) {
    this.existingAccount = existingAccount;
  }

  public Account getExistingAccount() {
    return existingAccount;
  }
}
