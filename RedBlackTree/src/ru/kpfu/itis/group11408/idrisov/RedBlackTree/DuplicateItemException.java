package ru.kpfu.itis.group11408.idrisov.RedBlackTree;
/** * Created by Татагромодуль on 19.04.2015. */

public class DuplicateItemException extends RuntimeException
{

    public DuplicateItemException( )
    {
        super( );
    }

    public DuplicateItemException( String message )
    {
        super( message );
    }
}