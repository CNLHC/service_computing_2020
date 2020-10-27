package provider

import (
	"context"
	"fmt"

	hessian "github.com/apache/dubbo-go-hessian2"

	"buaa/sc/assign1/server/pkg/model"

	"github.com/apache/dubbo-go/config"
)

type JavaString struct {
	payload string
}

func (u JavaString) JavaClassName() string {
	return "java.lang.String"
}

func init() {
	config.SetProviderService(new(PersonProvider))
	hessian.RegisterPOJO(&JavaString{})
}

type PersonProvider struct {
}

func (u *PersonProvider) SetName(ctx context.Context, req interface{}, resp *bool) error {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	person.Name = req.(string)
	Db.Save(&person)
	*resp = true
	return nil
}

func (u *PersonProvider) SetAge(ctx context.Context, req interface{}, resp *bool) error {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	person.Age = int(req.(int32))
	Db.Save(&person)
	*resp = true
	return nil
}

func (u *PersonProvider) SetGender(ctx context.Context, req interface{}, resp *bool) error {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	person.Gender = req.(bool)
	Db.Save(&person)
	*resp = true
	return nil
}

func (u *PersonProvider) SayHello(ctx context.Context) (string, error) {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	return fmt.Sprintf("Hello World! %s", person.Name), nil
}

func (u *PersonProvider) GetName(ctx context.Context) (string, error) {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	return person.Name, nil
}

func (u *PersonProvider) GetAge(ctx context.Context) (int, error) {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	return person.Age, nil
}

func (u *PersonProvider) GetGender(ctx context.Context) (bool, error) {
	Db := model.GetDB()
	var person model.Person
	Db.FirstOrCreate(&person, model.Person{ID: 1})
	return person.Gender, nil
}

func (u *PersonProvider) Reference() string {
	return "PersonProvider"
}
