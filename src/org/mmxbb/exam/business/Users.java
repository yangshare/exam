package org.mmxbb.exam.business;



import java.util.Collection;
import java.util.Vector;

public class Users {
  private Collection _users = new Vector();

  public void addUser(User user) {
    _users.add(user);
  }

  public Collection getUsers() {
    return this._users;
  }
}
