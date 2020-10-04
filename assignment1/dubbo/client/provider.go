package main

import (
	"context"

//	hessian "github.com/apache/dubbo-go-hessian2"
	"github.com/apache/dubbo-go/config"
)

type PersonProvider struct {
    GetName func(ctx context.Context, rsp * string) error
	SetName func(ctx context.Context,name string) error
    GetAge func(ctx context.Context, rsp * uint16) error
	SetAge func(ctx context.Context, aget uint16) error
    GetGender func(ctx context.Context, rsp * bool) error
	SetGender func(ctx context.Context,gender bool) error
}

func (u *PersonProvider) Reference() string {
    return "PersonProvider"
}

var  personProvider = new( PersonProvider )

func init(){
    config.SetConsumerService(personProvider)
}

