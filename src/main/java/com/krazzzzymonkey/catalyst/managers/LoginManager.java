/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import com.krazzzzymonkey.catalyst.utils.LoginUtils;

public class LoginManager
{
    public boolean cracked;
    public boolean favourites;
    public String name;
    public String email;
    public String password;
    
    public void setCracked() {
        this.name = this.email;
        this.password = null;
        this.cracked = true;
    }
    
    public LoginManager(final String email, String name, final String password, final boolean favourites) {
        this.email = email;
        this.favourites = favourites;
        if (password == null || password.isEmpty()) {
            name = email;
            this.password = null;
            this.cracked = true;
        }
        else {
            this.name = name;
            this.password = password;
            this.cracked = false;
        }
    }
    
    public LoginManager(final String s, final String password, final boolean favourites) {
        this.email = s;
        this.favourites = favourites;
        if (password == null || password.isEmpty()) {
            this.name = s;
            this.password = null;
            this.cracked = true;
        }
        else {
            this.name = LoginUtils.getName(s, password);
            this.password = password;
            this.cracked = false;
        }
    }
    
    public LoginManager(final String s) {
        this(s, false);
    }
    
    public String getName() {
        if (this.name != null) {
            return this.name;
        }
        if (this.email != null) {
            return this.email;
        }
        return "";
    }
    
    public boolean isFavourites() {
        return this.favourites;
    }
    
    public boolean isCracked() {
        return this.cracked;
    }
    
    public void setEmail(final String s) {
        this.email = s;
        if (this.password == null || this.password.isEmpty()) {
            this.name = s;
            this.password = null;
            this.cracked = true;
        }
        else {
            this.name = LoginUtils.getName(s, this.password);
            this.cracked = false;
        }
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setFavourites(final boolean favourites) {
        this.favourites = favourites;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public LoginManager(final String s, final String s2) {
        this(s, s2, false);
    }
    
    public void setPassword(final String s) {
        this.password = s;
        if (s == null || s.isEmpty()) {
            this.name = this.email;
            this.cracked = true;
        }
        else {
            this.name = LoginUtils.getName(this.email, s);
            this.password = s;
            this.cracked = false;
        }
    }
    
    public String getPassword() {
        if (this.password == null || this.password.isEmpty()) {
            this.cracked = true;
            return "";
        }
        return this.password;
    }
    
    public LoginManager(final String s, final boolean favourites) {
        this.email = s;
        this.name = s;
        this.password = null;
        this.cracked = true;
        this.favourites = favourites;
    }
}
