package main

var message string

func saveMessage(text string) { message = text }

func getMessage() string { return message }
