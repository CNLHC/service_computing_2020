package provider

import (
	"context"
	"fmt"

	"github.com/apache/dubbo-go/config"
	//"buaa/sc/pkg/model"
)

func init() {
	config.SetProviderService(new(PersonProvider))
}


type PersonProvider struct {
}

func (u *PersonProvider) SetName(ctx context.Context, req interface{}) error {
    fmt.Printf("req: %v",req)
	return nil
}

func (u *PersonProvider) SetAge(ctx context.Context, req interface{}) (string,error) {
	return "Some Name",nil
}

func (u *PersonProvider) SetGender(ctx context.Context, req interface{}) (string,error) {
	return "Some Name",nil
}

func (u *PersonProvider) GetName(ctx context.Context) (string,error) {
  return "Some Name",nil
}


func (u *PersonProvider) GetAge(ctx context.Context) (uint16,error) {
	return 22,nil
}

func (u *PersonProvider) GetGender(ctx context.Context) (bool , error){
	return false,nil
}

func (u *PersonProvider) Reference() string {
	return "PersonProvider"
}

