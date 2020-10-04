package model

import (
	"sync"

	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

type Person struct {
	gorm.Model
	Name   string
	Age    int
	Gender bool
}

func (u Person) JavaClassName() string {
	return "buaa.sc.assignment1.Person"
}

var (
	dbOnce sync.Once
	gormDb *gorm.DB
)

func GetDB() *gorm.DB {
	dbOnce.Do(func() {
		if db, err := gorm.Open(sqlite.Open("gorm.db"), &gorm.Config{}); err != nil {
			panic(err.Error())
		} else {
			gormDb = db
			db.AutoMigrate(&Person{})
		}
	})
	return gormDb
}
