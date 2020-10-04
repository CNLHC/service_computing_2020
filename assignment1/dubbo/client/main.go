package main

import (
	"context"
	"fmt"
	"time"


	_ "github.com/apache/dubbo-go/common/proxy/proxy_factory"
	 "github.com/apache/dubbo-go/config"

	_ "github.com/apache/dubbo-go/protocol/dubbo"
	_ "github.com/apache/dubbo-go/registry/protocol"
	_ "github.com/apache/dubbo-go/filter/filter_impl"

	_ "github.com/apache/dubbo-go/registry/zookeeper"
	_ "github.com/apache/dubbo-go/cluster/cluster_impl"
	_ "github.com/apache/dubbo-go/cluster/loadbalance"

	"github.com/spf13/cobra"

)
var (
    name string
    age     uint16
    gender bool
    method string
	rootCmd = &cobra.Command{
		Use:   "cobra",
		Short: "A generator for Cobra based Applications",
		Long: `Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
    Run:func(cmd *cobra.Command, args []string){
        config.Load()
        time.Sleep(3e9)
        switch method{
        case "GetName":
            var person_name string
            personProvider.GetName(context.TODO(), &person_name)
            println("Person's name: %s", person_name)
        case "SetName":
            personProvider.SetName(context.TODO(), name)
            println("Set Person's name to :%s",name)
        case "GetAge":
            var person_age uint16
            personProvider.GetAge(context.TODO(), &person_age)
            println("Person's age: %d",person_age)
        case "SetAge":
            personProvider.SetAge(context.TODO(), age)
            println("Set Person's age to :%d", age)
        case "GetGender":
            var person_gender bool
            personProvider.GetGender(context.TODO(), &person_gender)
            println("Person's name: %v", person_gender)
        case "SetGender":
            personProvider.SetGender(context.TODO(), gender)
            println("Set Person's  gender to :%v", gender)
        default:
            fmt.Printf("%s is not an available procedure\n",method)
        }
    },
	}
)

func init() {
    rootCmd.Flags().StringVarP(&name,"name","n","CNLHC","person name")
    rootCmd.Flags().Uint16VarP(&age,"age","a",22,"person age")
    rootCmd.Flags().BoolVarP(&gender,"g","g",true,"person gender(is male?)")
    rootCmd.Flags().StringVarP(&method,"method","m","GetName","RPC Method")
}

func main() {
    rootCmd.Execute()
}




