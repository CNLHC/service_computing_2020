package main

import (
	"context"
	"fmt"
	"log"

	_ "github.com/apache/dubbo-go/common/proxy/proxy_factory"
	"github.com/apache/dubbo-go/config"

	_ "github.com/apache/dubbo-go/filter/filter_impl"
	_ "github.com/apache/dubbo-go/protocol/dubbo"
	_ "github.com/apache/dubbo-go/registry/protocol"

	_ "github.com/apache/dubbo-go/cluster/cluster_impl"
	_ "github.com/apache/dubbo-go/cluster/loadbalance"
	_ "github.com/apache/dubbo-go/registry/zookeeper"

	"io/ioutil"

	"github.com/spf13/cobra"
)

var (
	name    string
	age     uint16
	gender  bool
	method  string
	rootCmd = &cobra.Command{
		Use:   "cobra",
		Short: "A generator for Cobra based Applications",
		Long: `Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
		Run: func(cmd *cobra.Command, args []string) {
			config.Load()
			fmt.Printf("method: %s\n", method)
			switch method {
			case "GetName":
				var person_name string
				personProvider.GetName(context.TODO(), &person_name)
				fmt.Printf("name: %s\n", person_name)
			case "GetAge":
				var person_age uint16
				personProvider.GetAge(context.TODO(), &person_age)
				fmt.Printf("age: %d\n", person_age)
			case "GetGender":
				var person_gender bool
				personProvider.GetGender(context.TODO(), &person_gender)
				fmt.Printf("gender: %v\n", person_gender)
			case "SetName":
				personProvider.SetName(context.TODO(), name)
				fmt.Printf("set name to: %s\n", name)
			case "SetAge":
				personProvider.SetAge(context.TODO(), age)
				fmt.Printf("set age to: %d\n", age)
			case "SetGender":
				personProvider.SetGender(context.TODO(), gender)
				fmt.Printf("set gender to: %v\n", gender)
			default:
				fmt.Printf("%s is not an available procedure\n", method)
			}
		},
	}
)

func init() {
	log.SetOutput(ioutil.Discard)
	rootCmd.Flags().StringVarP(&name, "name", "n", "CNLHC", "person name")
	rootCmd.Flags().Uint16VarP(&age, "age", "a", 22, "person age")
	rootCmd.Flags().BoolVarP(&gender, "g", "g", true, "person gender(is male?)")
	rootCmd.Flags().StringVarP(&method, "method", "m", "GetName", "RPC Method")
}

func main() {
	rootCmd.Execute()
}
