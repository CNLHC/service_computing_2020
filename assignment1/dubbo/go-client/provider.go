package main

import (
	"context"

	//	hessian "github.com/apache/dubbo-go-hessian2"
	"github.com/apache/dubbo-go/config"
)

type PersonProvider struct {
	GetName   func(ctx context.Context, rsp *string) error
	GetAge    func(ctx context.Context, rsp *uint16) error
	GetGender func(ctx context.Context, rsp *bool) error

	SetName   func(ctx context.Context, name string) (bool, error)
	SetAge    func(ctx context.Context, aget int32) (bool, error)
	SetGender func(ctx context.Context, gender bool) (bool, error)
}

func (u *PersonProvider) Reference() string {
	return "PersonProvider"
}

var personProvider = new(PersonProvider)

func init() {
	config.SetConsumerService(personProvider)
}
